import * as React from 'react';

import { ExpoTrustedTimeViewProps } from './ExpoTrustedTime.types';

export default function ExpoTrustedTimeView(props: ExpoTrustedTimeViewProps) {
  return (
    <div>
      <span>{props.name}</span>
    </div>
  );
}
