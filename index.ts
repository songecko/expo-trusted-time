import { NativeModulesProxy, EventEmitter, Subscription } from 'expo-modules-core';

// Import the native module. On web, it will be resolved to ExpoTrustedTime.web.ts
// and on native platforms to ExpoTrustedTime.ts
import ExpoTrustedTimeModule from './src/ExpoTrustedTimeModule';
import ExpoTrustedTimeView from './src/ExpoTrustedTimeView';
import { ChangeEventPayload, ExpoTrustedTimeViewProps } from './src/ExpoTrustedTime.types';

// Get the native constant value.
export const PI = ExpoTrustedTimeModule.PI;

export function hello(): string {
  return ExpoTrustedTimeModule.hello();
}

export async function setValueAsync(value: string) {
  return await ExpoTrustedTimeModule.setValueAsync(value);
}

const emitter = new EventEmitter(ExpoTrustedTimeModule ?? NativeModulesProxy.ExpoTrustedTime);

export function addChangeListener(listener: (event: ChangeEventPayload) => void): Subscription {
  return emitter.addListener<ChangeEventPayload>('onChange', listener);
}

export { ExpoTrustedTimeView, ExpoTrustedTimeViewProps, ChangeEventPayload };
