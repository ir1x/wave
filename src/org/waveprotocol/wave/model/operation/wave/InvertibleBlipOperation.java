/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.waveprotocol.wave.model.operation.wave;

import org.waveprotocol.wave.model.wave.data.BlipData;

import org.waveprotocol.wave.model.operation.OperationException;

import java.util.Collections;
import java.util.List;

/**
 * A wavelet operation that can be inverted.
 *
 */
public abstract class InvertibleBlipOperation extends BlipOperation {

  protected InvertibleBlipOperation(WaveletOperationContext context) {
    super(context);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final List<BlipOperation> applyAndReturnReverse(BlipData target)
      throws OperationException {
    WaveletOperationContext reverseContext = createReverseContext(target);
    doUpdate(target);
    doApply(target);
    return Collections.singletonList(invert(reverseContext));
  }

  /**
   * @return The inverse of this operation.
   */
  protected abstract BlipOperation invert(WaveletOperationContext newContext);

}
