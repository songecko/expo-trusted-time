import { requireNativeViewManager } from 'expo-modules-core';
import * as React from 'react';

import { ExpoTrustedTimeViewProps } from './ExpoTrustedTime.types';

const NativeView: React.ComponentType<ExpoTrustedTimeViewProps> =
  requireNativeViewManager('ExpoTrustedTime');

export default function ExpoTrustedTimeView(props: ExpoTrustedTimeViewProps) {
  return <NativeView {...props} />;
}
