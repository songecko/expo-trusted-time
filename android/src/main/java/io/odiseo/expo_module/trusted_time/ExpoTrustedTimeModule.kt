package io.odiseo.expo_module.trusted_time

import expo.modules.kotlin.modules.Module
import expo.modules.kotlin.modules.ModuleDefinition
import android.content.Context
import com.google.android.gms.tasks.Task
import com.google.android.gms.time.TrustedTime
import com.google.android.gms.time.TrustedTimeClient

class TrustedTimeClientAccessor(private val context: Context) {
  fun createClient(): Task<TrustedTimeClient> {
    return TrustedTime.createClient(context)
  }
}

class ExpoTrustedTimeModule : Module() {
  private lateinit var trustedTimeClientAccessor: TrustedTimeClientAccessor
  private var trustedTimeClient: TrustedTimeClient? = null
  private val context
    get() = requireNotNull(appContext.reactContext)

  // Each module class must implement the definition function. The definition consists of components
  // that describes the module's functionality and behavior.
  // See https://docs.expo.dev/modules/module-api for more details about available components.
  override fun definition() = ModuleDefinition {
    OnCreate {
      trustedTimeClientAccessor = TrustedTimeClientAccessor(context)
      trustedTimeClientAccessor.createClient().addOnCompleteListener { task ->
        if (task.isSuccessful) {
          trustedTimeClient = task.result
        } else {
          val exceptionMessage = task.exception?.message ?: "Unknown error calling TrustedTime.createClient"
          throw IllegalStateException(exceptionMessage, task.exception)
        }
      }
    }
    // Sets the name of the module that JavaScript code will use to refer to the module. Takes a string as an argument.
    // Can be inferred from module's class name, but it's recommended to set it explicitly for clarity.
    // The module will be accessible from `requireNativeModule('ExpoTrustedTime')` in JavaScript.
    Name("ExpoTrustedTime")

    // Sets constant properties on the module. Can take a dictionary or a closure that returns a dictionary.
    Constants(
      "PI" to Math.PI
    )

    // Defines event names that the module can send to JavaScript.
    Events("onChange")

    // Defines a JavaScript synchronous function that runs the native code on the JavaScript thread.
    Function("getCurrentTimeInMillis") {
      val currentTimeMillis =
        trustedTimeClient?.computeCurrentUnixEpochMillis()
          ?: System.currentTimeMillis()
      currentTimeMillis
    }

    // Defines a JavaScript function that always returns a Promise and whose native code
    // is by default dispatched on the different thread than the JavaScript runtime runs on.
    AsyncFunction("setValueAsync") { value: String ->
      // Send an event to JavaScript.
      sendEvent("onChange", mapOf(
        "value" to value
      ))
    }

    // Enables the module to be used as a native view. Definition components that are accepted as part of
    // the view definition: Prop, Events.
    View(ExpoTrustedTimeView::class) {
      // Defines a setter for the `name` prop.
      Prop("name") { view: ExpoTrustedTimeView, prop: String ->
        println(prop)
      }
    }
  }
}
