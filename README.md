# Expo Trusted Time

A React Native module for Expo that provides a reliable way to retrieve the current time using Google's Trusted Time API.
This ensures accurate and tamper-proof time synchronization for your applications.

## Features

- Fetches the current time with high precision
- Uses Google Play Services' Trusted Time API
- Works seamlessly with Expo

## Installation

To install the module, run the following command in your Expo project:

```bash
npm install @songecko/expo-trusted-time
```

## Usage
Import the module into your JavaScript code:

```javascript
import ExpoTrustedTimeModule from '@songecko/expo-trusted-time';

async function getTrustedTime() {
  const trustedTimeMillis = await ExpoTrustedTimeModule.getCurrentTimeInMillis();
  console.log('Trusted Time (UTC):', new Date(trustedTimeMillis).toISOString());
}

getTrustedTime();
```

## License
This module is licensed under the MIT License. See the LICENSE file for more details.

## Author
Diego D'amico <songecko@gmail.com> (https://github.com/songecko)