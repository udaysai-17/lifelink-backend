package com.lifelink.util;

import java.util.EnumSet;
import java.util.Map;

import com.lifelink.enums.BloodGroup;

public class BloodCompatibilityUtil {

    private static final Map<BloodGroup, EnumSet<BloodGroup>> COMPATIBILITY_MAP =
            Map.of(
                    BloodGroup.O_NEGATIVE,
                    EnumSet.of(
                            BloodGroup.O_NEGATIVE,
                            BloodGroup.O_POSITIVE,
                            BloodGroup.A_NEGATIVE,
                            BloodGroup.A_POSITIVE,
                            BloodGroup.B_NEGATIVE,
                            BloodGroup.B_POSITIVE,
                            BloodGroup.AB_NEGATIVE,
                            BloodGroup.AB_POSITIVE),

                    BloodGroup.O_POSITIVE,
                    EnumSet.of(
                            BloodGroup.O_POSITIVE,
                            BloodGroup.A_POSITIVE,
                            BloodGroup.B_POSITIVE,
                            BloodGroup.AB_POSITIVE),

                    BloodGroup.A_NEGATIVE,
                    EnumSet.of(
                            BloodGroup.A_NEGATIVE,
                            BloodGroup.A_POSITIVE,
                            BloodGroup.AB_NEGATIVE,
                            BloodGroup.AB_POSITIVE),

                    BloodGroup.A_POSITIVE,
                    EnumSet.of(
                            BloodGroup.A_POSITIVE,
                            BloodGroup.AB_POSITIVE),

                    BloodGroup.B_NEGATIVE,
                    EnumSet.of(
                            BloodGroup.B_NEGATIVE,
                            BloodGroup.B_POSITIVE,
                            BloodGroup.AB_NEGATIVE,
                            BloodGroup.AB_POSITIVE),

                    BloodGroup.B_POSITIVE,
                    EnumSet.of(
                            BloodGroup.B_POSITIVE,
                            BloodGroup.AB_POSITIVE),

                    BloodGroup.AB_NEGATIVE,
                    EnumSet.of(
                            BloodGroup.AB_NEGATIVE,
                            BloodGroup.AB_POSITIVE),

                    BloodGroup.AB_POSITIVE,
                    EnumSet.of(
                            BloodGroup.AB_POSITIVE));
    
    
    private BloodCompatibilityUtil() {
    }

    public static boolean isCompatible(
            BloodGroup donorBloodGroup,
            BloodGroup recipientBloodGroup) {

        return COMPATIBILITY_MAP
                .getOrDefault(donorBloodGroup, EnumSet.noneOf(BloodGroup.class))
                .contains(recipientBloodGroup);
    }

}