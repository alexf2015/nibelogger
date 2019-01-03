/**
 * Copyright (c) 2010-2017 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package de.afriese.iot.nibe.connector;

public interface UplinkConstants {
    public static final String LOGIN_URL = "https://www.nibeuplink.com/LogIn";
    public static final String DATA_API_URL = "https://www.nibeuplink.com/PrivateAPI/Values";

    public static final String LOGIN_FIELD_PASSWORD = "Password";
    public static final String LOGIN_FIELD_EMAIL = "Email";
    public static final String LOGIN_FIELD_RETURN_URL = "returnUrl";

    public static final String DATA_API_FIELD_LAST_DATE = "currentWebDate";
    public static final String DATA_API_FIELD_LAST_DATE_DEFAULT_VALUE = "01.01.2017 13:37:42";
    public static final String DATA_API_FIELD_ID = "hpid";
    public static final String DATA_API_FIELD_DATA = "variables";
    public static final String DATA_API_FIELD_DATA_DEFAULT_VALUE = "0";
}
