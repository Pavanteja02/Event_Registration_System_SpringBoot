package com.abc.event_registration_system.common;

import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class DeviceProvider {

    public Device getCurrentDevice(HttpServletRequest request) {
        return DeviceUtils.getCurrentDevice(request);
    }
}
