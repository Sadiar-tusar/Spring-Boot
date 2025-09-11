package com.sadiar.erp.service;

import com.sadiar.erp.entity.Employee;
import com.sadiar.erp.entity.Role;
import com.sadiar.erp.entity.User;
import com.sadiar.erp.repository.IEmployeeRepo;
import com.sadiar.erp.repository.IUserRepo;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private IUserRepo userRepo;
    @Autowired
    private IEmployeeRepo employeeRepo;

    @Autowired
    private EmailService emailService;

    @Autowired
    private EmployeeService employeeService;


    @Value("src/main/resources/static/images")
    private String uploadDir;

    public void saveOrUpdate(User user, MultipartFile imageFile) {
        if (imageFile != null && !imageFile.isEmpty()) {
            String filename = saveImage(imageFile, user);
            user.setPhoto(filename);
        }


        user.setRole(Role.EMPLOYEE);
        userRepo.save(user);
//        sendActivationEmail(user);
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public User findByEmail(String email) {
        return userRepo.findByEmail(email).get();
    }

    public void delete(User user) {
        userRepo.delete(user);
    }

    private void sendActivationEmail(User user) {
        String subject = "Welcome to Our Service – Confirm Your Registration";

        String activationLink="http://localhost:8085/api/user/active/"+user.getId();

        String mailText = "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<style>"
                + "  body { font-family: Arial, sans-serif; line-height: 1.6; }"
                + "  .container { max-width: 600px; margin: auto; padding: 20px; border: 1px solid #e0e0e0; border-radius: 10px; }"
                + "  .header { background-color: #4CAF50; color: white; padding: 10px; text-align: center; border-radius: 10px 10px 0 0; }"
                + "  .content { padding: 20px; }"
                + "  .footer { font-size: 0.9em; color: #777; margin-top: 20px; text-align: center; }"
                + "</style>"
                + "</head>"
                + "<body>"
                + "  <div class='container'>"
                + "    <div class='header'>"
                + "      <h2>Welcome to Our Platform</h2>"
                + "    </div>"
                + "    <div class='content'>"
                + "      <p>Dear " + user.getName() + ",</p>"
                + "      <p>Thank you for registering with us. We are excited to have you on board!</p>"
                + "      <p>Please confirm your email address to activate your account and get started.</p>"
                + "      <p>If you have any questions or need help, feel free to reach out to our support team.</p>"
                + "      <br>"
                + "      <p>Best regards,<br>The Support Team</p>"
                + "      <p>To Activate Your Account, please click the following link:</p>"
                + "      <p><a href=\"" + activationLink + "\">Activate Account</a></p>"
                + "    </div>"
                + "    <div class='footer'>"
                + "      &copy; " + java.time.Year.now() + " YourCompany. All rights reserved."
                + "    </div>"
                + "  </div>"
                + "</body>"
                + "</html>";

        try {
            emailService.sendSimpleEmail(user.getEmail(), subject, mailText);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send activation email", e);
        }
    }

    // for User folder
    public String saveImage(MultipartFile file, User user) {

        Path uploadPath = Paths.get(uploadDir + "/users");
        if (!Files.exists(uploadPath)) {
            try {
                Files.createDirectory(uploadPath);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        String fileName = user.getName() + "_" + UUID.randomUUID().toString();


        try {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileName;

    }

    // for User folder
    public String saveImageForEmployee(MultipartFile file, Employee employee) {

        Path uploadPath = Paths.get(uploadDir + "/employee");
        if (!Files.exists(uploadPath)) {
            try {
                Files.createDirectory(uploadPath);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        String employeerName = employee.getName();
        String fileName = employeerName.trim().replaceAll("\\s+", "_");

        String savedFileName = fileName + "_" + UUID.randomUUID().toString();

        try {
            Path filePath = uploadPath.resolve(savedFileName);
            Files.copy(file.getInputStream(), filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return savedFileName;

    }


//    public void registerEmployee(User user, MultipartFile imageFile, Employee employee) {
//        if (imageFile != null && !imageFile.isEmpty()) {
//            // Save image for both User and employee
//            String filename = saveImage(imageFile, user);
//            String employeePhoto = saveImageForEmployee(imageFile, employee);
//            employee.setPhoto(employeePhoto);
//            user.setPhoto(filename);
//        }
//
//        // Encode password before saving User
//        user.setPassword(user.getPassword());
//        user.setRole(Role.EMPLOYEE);
////        user.setActive(false);
//
//        // Save User FIRST and get persisted instance
//        User savedUser = userRepo.save(user);
//
//        // Now, associate saved User with JobSeeker and save JobSeeker
//        employee.setUser(savedUser);
//        employeeService.createEmployee(employee);
//
////        // Now generate token and save Token associated with savedUser
////        String jwt = jwtService.generateToken(savedUser);
////        saveUserToken(jwt, savedUser);
////
////        // Send Activation Email
////        sendActivationEmail(savedUser);
//    }

//Employ plus user Save
public void registerEmployee(User user, MultipartFile imageFile, Employee employeeData) {
    if (imageFile != null && !imageFile.isEmpty()) {
        String filename = saveImage(imageFile, user);
        String employeePhoto = saveImageForEmployee(imageFile, employeeData);
        employeeData.setPhoto(employeePhoto);
        user.setPhoto(filename);
    }
    user.setPassword(user.getPassword());
    user.setRole(Role.EMPLOYEE);
//    user.setActive(true);
    User savedUser = userRepo.save(user);

    employeeData.setUser(savedUser);

//    if (employeeData.getDateOfJoining() != null) {
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(employeeData.getDateOfJoining());
//        cal.add(Calendar.YEAR,30); //joining date theke 30 year.
//        employeeData.setRetirementDate(cal.getTime());
//    }
    employeeService.createEmployee(employeeData);

//    sendEmployeeWelcomeEmail(employeeData);

}



}
