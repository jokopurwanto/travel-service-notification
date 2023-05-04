package com.travel.notification.service.Imple;

import com.travel.notification.db.userdb.model.OrderModel;
import com.travel.notification.db.userdb.model.UserModel;
import com.travel.notification.db.userdb.repository.OrderRepository;
import com.travel.notification.db.userdb.repository.UserRepository;
import com.travel.notification.dto.NotificationCreateDto;
import com.travel.notification.dto.NotificationUpdateDto;
import com.travel.notification.handler.NotificationNotFoundException;
import com.travel.notification.db.notificationdb.model.NotificationModel;
import com.travel.notification.db.notificationdb.repository.NotificationRepository;
import com.travel.notification.service.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.ZonedDateTime;

@Service
public class NotificationService implements INotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public NotificationModel createNotification(NotificationCreateDto notificationCreateDto) {

        ZonedDateTime now = ZonedDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now.toLocalDateTime());
        Boolean status;

        //validasi email
        UserModel userModel = userRepository.findById(notificationCreateDto.getIdUser()).get();
        if(userModel.getEmail() == null){
            System.out.println("email tidak ditemukan "+userModel.getEmail());
            status = false;
        }else {
            System.out.println("email ditemukan "+userModel.getEmail());
            //send email
            sendEmail(userModel.getEmail(),"Travel App - Bukti Transaksi", contentBodyEmail(userModel.getUsername(),
                    notificationCreateDto.getDestination(),
                    notificationCreateDto.getStartDate(),
                    notificationCreateDto.getEndDate(),
                    notificationCreateDto.getTotalPerson(),
                    notificationCreateDto.getTotalPrice()));
            //end send email

            status = true;
        }

        //insert db notification
        NotificationModel notificationModel = NotificationModel.builder()
                .status(status)
                .email(userModel.getEmail())
                .createdAt(timestamp)
                .build();
        NotificationModel notifMdl = notificationRepository.saveAndFlush(notificationModel);
        //end insert db notification


        //update db transaction
        OrderModel orderMdlTmp = orderRepository.findById(notificationCreateDto.getIdOrder()).get();
        OrderModel orderModel = OrderModel.builder()
                .id(notificationCreateDto.getIdOrder())
                .idUser(orderMdlTmp.getIdUser())
                .idPayment(orderMdlTmp.getIdPayment())
                .idNotification(notifMdl.getId())
                .date(orderMdlTmp.getDate())
                .startDate(orderMdlTmp.getStartDate())
                .endDate(orderMdlTmp.getEndDate())
                .totalPerson(orderMdlTmp.getTotalPerson())
                .build();
        orderRepository.save(orderModel);
        //end update db transaction

        return notificationModel;
    }

    @Override
    public NotificationModel updateNotification(NotificationUpdateDto notificationUpdateDto, Integer id){
        if(notificationRepository.findById(id).isEmpty())
            throw new NotificationNotFoundException("Data notification yang akan di-update tidak ditemukan");

        NotificationModel notificationModel = NotificationModel.builder()
                .id(id)
                .status(notificationUpdateDto.getStatus())
                .email(notificationUpdateDto.getEmail())
                .createdAt(notificationUpdateDto.getCreatedAt())
                .build();
        notificationRepository.save(notificationModel);
        return notificationRepository.findById(id).get();
    }

    @Override
    public List<NotificationModel> getAllNotification() {
        System.out.println(notificationRepository.findAll());
        return notificationRepository.findAll();
    }

    @Override
    public Map<String, Object> deleteNotification(Integer id) {
        if(notificationRepository.findById(id).isEmpty())
            throw new NotificationNotFoundException("Data yang dicari tidak ditemukan");

        NotificationModel notificationModel = notificationRepository.findById(id).get();
        Map<String,Object> response = new HashMap<>();
        response.put("id", notificationModel.getId());
        response.put("status", notificationModel.getStatus());
        response.put("email", notificationModel.getEmail());
        response.put("createdAt",notificationModel.getCreatedAt());
        notificationRepository.deleteById(id);
        return response;
    }



    @Override
    public NotificationModel getNotificationByID(Integer id) {
        if(notificationRepository.findById(id).isEmpty())
            throw new NotificationNotFoundException("Data yang dicari tidak ditemukan");
        return notificationRepository.findById(id).get();
    }

    @Override
    public NotificationModel get(Integer id){
        return notificationRepository.findById(id).get();
    }

    @Override
    public void save(NotificationModel notificationModel){
        notificationRepository.save(notificationModel);
    }

    @Override
    public void delete(Integer id){
        notificationRepository.deleteById(id);
    }

    @Override
    public List<UserModel> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public void sendEmail(String toEmail, String subject, String body) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("mailspring389@gmail.com");
        mailMessage.setTo(toEmail);
        mailMessage.setText(body);
        mailMessage.setSubject(subject);

        javaMailSender.send(mailMessage);
        System.out.println("Pengiriman email sukses");
    }

    @Override
    public String contentBodyEmail(String username, String destination, Date startDate, Date endDate, Integer totalPerson, String totalPrice) {
        String contentBody = "Hai "+username+"\r\n" +
                "Anda baru saja melakukan transaksi menggunakan Travel Apps.\r\n" +
                "Berikut ini adalah detail transaksi Anda :\r\n" +
                "Status\t:\tBerhasil\r\n" +
                "Tujuan\t:\t"+destination+"\r\n" +
                "Dari Tanggal\t:\t"+startDate+"\n" +
                "Sampai Tanggal\t:\t"+endDate+"\n" +
                "Total Pengunjung\t:\t"+totalPerson+"\n" +
                "Total Pembayaran\t:\t"+totalPrice+"\n" +

                "Mohon simpan email ini sebagai referensi transaksi Anda.\n" +
                "Terima kasih.\n" +
                "\r\n" +
                "TREVEL APPS\r\n" +
                "Jl. Slipi Jaya No. 1 Jakarta Barat\r\n" +
                "Telp : (021) 08123456789";

        return contentBody;
    }
}
