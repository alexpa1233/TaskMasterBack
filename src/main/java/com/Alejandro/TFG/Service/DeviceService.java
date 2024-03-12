package com.Alejandro.TFG.Service;



import com.Alejandro.TFG.model.Device;
import com.Alejandro.TFG.model.Notification;



public interface DeviceService { 
    
    Device getDeviceById(Long id); 
    Device saveDevice(Device device);
    void deleteDevice(Long id);
    Device registerDevice(Device dispositivo);
    void sendNotification(Notification notification, Long deviceId);
}
