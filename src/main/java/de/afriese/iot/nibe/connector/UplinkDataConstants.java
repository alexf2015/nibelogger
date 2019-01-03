/**
 * Copyright (c) 2010-2017 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package de.afriese.iot.nibe.connector;

import de.afriese.iot.DataConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UplinkDataConstants implements DataConstants {

    // Raumzieltemperatur
    TEMP_ROOM_TARGET("47398", "Raumzieltemperatur"),

    // Raumtemperatur BT50
    TEMP_ROOM_BT50("40033", "Raumtemperatur aktuell"),

    // Vorlauf Heizung BT2
    TEMP_FORWARD_BT2("40008", "Vorlauf Heizung BT2"),

    // Rücklauf Heizung BT3
    TEMP_RETURN_BT3("40012", "Rücklauf Heizung BT3"),

    // Pumpengeschw. Wärmetr. GP1
    SPEED_GP1("44396", "Pumpengeschw. Wärmetr. GP1"),

    // BW, inkl. int. ZH
    HW_INCL_ADD("44298", "BW, inkl. int. ZH"),

    // BW, nur Verd.
    HW_COMP_ONLY("44306", "BW, nur Verd."),

    // (44300 oder 44308)
    HEAT_INCL_ADD("44300", "Heizung, inkl. int. Zusatz"),

    // (44300 oder 44308)
    HEAT_COMP_ONLY("44308", "Heizung, nur Verd."),

    // (44302 oder 44304)
    COOL_COMP_ONLY("44302", "Kühlung, nur Verd."),

    // (44302 oder 44304)
    POOL_COMP_ONLY("44304", "Pool, nur Verd."),

    // Außentemperatur
    TEMP_OUT_BT1("40004", "Außentemperatur"),

    // Außentemperatur
    TEMP_OUT_AVG_BT1("40067", "durchschn. Außentemperatur"),

    // akt. Leistung Zusatzheizung
    CUR_POW_INT_ADD("43084", "akt. Leistung Zusatzheizung"),

    // max Leistung Zusatzheizung
    MAX_POW_INT_ADD("47212", "max Leistung Zusatzheizung"),

    /* EB-101 spezifisch */

    // Kond.vorlauf EB101-BT12
    TEMP_EB101_BT12("44058", "Kond.vorlauf EB101-BT12"),

    // Rücklauftemp. EB101-BT3
    TEMP_EB101_BT3("44055", "Rücklauftemp. EB101-BT3"),

    // Betriebszeit Verdichter Brauchwasser EB101
    HOURS_EB101_COMP_HW("44073", "Betriebszeit Verdichter Brauchwasser EB101"),

    // Betriebszeit Verdichter gesamt EB101
    HOURS_EB101_COMP_HEAT("44071", "Betriebszeit Verdichter gesamt EB101"),

    // Betriebszeit Verdichter Kühlung EB101
    HOURS_EB101_COMP_COOL("40737", "Betriebszeit Verdichter Kühlung EB101"),

    // Verdichter Starts EB101
    COUNT_EB101_COMP_STARTS("44069", "Verdichter Starts EB101"),

    /* ERS 20-250 spezifisch */

    /* Abluft BT20 */
    TEMP_ERS20250_BT20("40025", "Temperatur Abluft"),

    /* Fortluft BT21 */
    TEMP_ERS20250_BT21("40026", "Temperatur Fortluft"),

    /* Zuluft BT22 */
    TEMP_ERS20250_BT22("40075", "Temperatur Zuluft"),

    /* Außentemperatur AZ30-BT23 */
    TEMP_ERS20250_BT23("40183", "Temperatur Frischluft"),

    /* Ventilatordrehzahl GQ2 */
    SPEED_FAN_ERS20250_GQ2("40311", "Ventilator Abluft"),

    /* Ventilatordrehzahl GQ3 */
    SPEED_FAN_ERS20250_GQ3("40312", "Ventilator Zuluft"),

    /* END */
    ;
    // nvps.add(new BasicNameValuePair("variables", "40311"));
    // Verdichter Status??? nvps.add(new BasicNameValuePair("variables", "48132"));
    // nvps.add(new BasicNameValuePair("variables", "47041"));
    // nvps.add(new BasicNameValuePair("variables", "40312"));
    // nvps.add(new BasicNameValuePair("variables", "47260"));
    // nvps.add(new BasicNameValuePair("variables", "43084"));
    // nvps.add(new BasicNameValuePair("variables", "43081"));
    // nvps.add(new BasicNameValuePair("variables", "40014"));
    // nvps.add(new BasicNameValuePair("variables", "40013"));
    // nvps.add(new BasicNameValuePair("variables", "40075"));
    // nvps.add(new BasicNameValuePair("variables", "40121"));
    // nvps.add(new BasicNameValuePair("variables", "44055"));

    private final String id;
    private final String name;

}
