package com.Alejandro.TFG.controller;

import java.net.MalformedURLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Alejandro.TFG.Service.DeviceService;
import com.Alejandro.TFG.model.Device;
import com.Alejandro.TFG.model.Notification;





@RestController
@RequestMapping("/api/device/")
public class DeviceController {
    @Autowired
    DeviceService deviceService;

    @GetMapping("/{id}")
    public  ResponseEntity<Device> getDeviceById(@PathVariable Long id){
        Device device = deviceService.getDeviceById(id);
        return new ResponseEntity<>(device, HttpStatus.OK);
    }


    @PutMapping("/updateDevice")
    public ResponseEntity<Device> updateDevice(@RequestBody Device device){
        Device savedDevice = deviceService.saveDevice(device);
         return new ResponseEntity<>(savedDevice, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteDevice(@RequestBody Device device){
        deviceService.deleteDevice(device.getId());
        return ResponseEntity.noContent().build();
    }

     @PostMapping("/saveDevice")
  public Device registerDevice(@RequestBody Device dispositivo) {
    return deviceService.registerDevice(dispositivo);
  }

  @PostMapping("/sendNotification/{deviceId}")
  public void sendNotification(@PathVariable() Long deviceId, @RequestBody Notification notification)
      throws MalformedURLException {
    deviceService.sendNotification(notification, deviceId);
  }
}
