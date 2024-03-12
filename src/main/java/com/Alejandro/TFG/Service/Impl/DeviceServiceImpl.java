package com.Alejandro.TFG.Service.Impl;

import java.util.Optional;
import java.io.OutputStream;
import java.net.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Alejandro.TFG.Service.DeviceService;
import com.Alejandro.TFG.model.Device;
import com.Alejandro.TFG.model.Notification;
import com.Alejandro.TFG.repository.DeviceRepository;




import jakarta.transaction.Transactional;

@Service
public class DeviceServiceImpl implements DeviceService{

    private static final String FCM_APIKEY ="AAAAQacgcAM:APA91bEhCQkyodPJWo2XIdKvXMejg3v7RK5xok4mDGFy4TmLNmEnFNnrrlfh55jPCuMpngoRz--dxNBZfMijGLUuthAoCOe9as5rpIyXpbrKr_ZXoEBr-hE3CmJ_WxDrksmHbMzX_hzC";

    private static final String URL = "https://fcm.googleapis.com/fcm/send";

    @Autowired
    DeviceRepository deviceRepository;

    @Override
    public Device getDeviceById(Long id) {
        return deviceRepository.findById(id).orElse(null);
    }

    @Override
    public Device saveDevice(Device device) {
       return deviceRepository.save(device);
    }

    @Override
    public void deleteDevice(Long id) { 
        deviceRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Device registerDevice(Device dispositivo) {
        Device device = deviceRepository.findByDeviceId(dispositivo.getDeviceId());
        if(device==null){
            return deviceRepository.save(dispositivo);
        }else{
            return dispositivo;
        }
  
    }


    @Override
    public void sendNotification(Notification notification, Long deviceId){

        Optional<Device> device = deviceRepository.findById(deviceId);
        if (device.isPresent()) {
            
            try {
                //Conexion
                URL url = new URL(URL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                //Configuracion de la conexion
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Authorization", "key=" + FCM_APIKEY);
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);
                //Construccion body 
                String body = buildNotificationBody(notification, device.get().getDeviceId());
                //Enviamos solicitud
                OutputStream os = conn.getOutputStream();
                os.write(body.getBytes());
                os.flush();

                //Verificamos la respuesta
                int responseCode = conn.getResponseCode();
                if(responseCode == HttpURLConnection.HTTP_OK){
                    System.out.println("Notification sent successfully");
                    return ;

                }else {
                    System.out.println("Error sending notification. Response code: " + responseCode);
                    throw new RuntimeException("Error sending notification. Response code: " + responseCode);
                }
    
            } catch (Exception e) {
                throw new RuntimeException("Exception occurred while sending notification", e);
            }
    
        } else {
            throw new RuntimeException("Device with ID " + deviceId + " not found");
        }

    }

    public static String buildNotificationBody(Notification notification, String deviceId){
        return String.format("{\"to\":\"%s\",\"notification\":{\"title\":\"%s\",\"body\":\"%s\"}}",deviceId,notification.getTitle(),notification.getMessage());
    }





    
}
