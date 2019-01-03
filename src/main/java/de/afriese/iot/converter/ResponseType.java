/**
 * Copyright (c) 2010-2017 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package de.afriese.iot.converter;

import de.afriese.iot.nibe.connector.NibeuplinkDataResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseType {


    NIBE_UPLINK(NibeuplinkDataResponse.class),

    ;

    private final Class<? extends DataResponse> responseClass;
}
