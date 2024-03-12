package com.Alejandro.TFG.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Alejandro.TFG.model.Device;


public interface DeviceRepository extends JpaRepository<Device, Long>{

    
    Device findByDeviceId(String deviceId);


    
} 
