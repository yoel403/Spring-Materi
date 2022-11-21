package com.example.LatSpring.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.LatSpring.model.dto.request.UserDto;
import com.example.LatSpring.model.dto.response.ResponseData;
import com.example.LatSpring.model.entity.DetailUser;
import com.example.LatSpring.model.entity.User;
import com.example.LatSpring.repository.DetailUserRepository;
import com.example.LatSpring.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserServices {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private DetailUserRepository detailUserRepository;

  private ResponseData<Object> responseData;
  private User user;
  private DetailUser detailUser;
  private Map<Object, Object> data;

  @Override
  public ResponseData<Object> register(UserDto request) {
    // TODO Auto-generated method stub
    // check user apakah usernya ini udh terdaftar atau belum, check emailnya
    // select * from users where email = ?
    Optional<User> userOpt = userRepository.findByEmail(request.getEmail());
    if (userOpt.isPresent()) {
      responseData = new ResponseData<Object>(HttpStatus.BAD_REQUEST.value(), "User is found, please login!", null);
    } else {
      user = new User(request.getEmail(), request.getPassword());
      // save to db
      userRepository.save(user);

      detailUser = new DetailUser(request.getFirstName(), request.getLastName(), request.getPhoneNumber());
      detailUser.setUserId(user);
      detailUser.setUserEmail(user);
      detailUserRepository.save(detailUser);

      // data spesific yg dikirim
      data = new HashMap<>();
      data.put("email", user.getEmail());
      data.put("firstName", detailUser.getFirstName());
      data.put("lastName", detailUser.getLastName());
      data.put("phoneNumber", detailUser.getPhoneNumber());

      // response
      responseData = new ResponseData<Object>(HttpStatus.CREATED.value(), "Success registerd", data);
    }
    return responseData;
  }

  @Override
  public ResponseData<Object> login(UserDto request) {
    // TODO Auto-generated method stub
    // check user
    Optional<User> userOpt = userRepository.findByEmail(request.getEmail());
    if (userOpt.isPresent()) {
      user = userOpt.get();

      if (request.getPassword().equals(user.getPassword())) {
        // data spesific
        data = new HashMap<>();
        data.put("email", user.getEmail());

        // response data
        responseData = new ResponseData<Object>(HttpStatus.OK.value(), "Success login.", data);
      } else {
        responseData = new ResponseData<Object>(HttpStatus.BAD_REQUEST.value(), "Wrong password", null);
      }

    } else {
      responseData = new ResponseData<Object>(HttpStatus.BAD_REQUEST.value(), "User is not found, please register",
          null);
    }
    return responseData;
  }

  @Override
  public ResponseData<Object> updateUser(UserDto request) {
    // TODO Auto-generated method stub
    Optional<User> userOpt = userRepository.findByEmail(request.getEmail());
    if (userOpt.isPresent()) {
      user = userOpt.get();
      Optional<DetailUser> detailUserOpt = detailUserRepository.findByUserEmail(user);
      if (detailUserOpt.isPresent()) {
        detailUser = detailUserOpt.get();
        // update user
        detailUser.setFirstName(request.getFirstName());
        detailUser.setLastName(request.getLastName());
        detailUser.setPhoneNumber(request.getPhoneNumber());

        // save
        detailUserRepository.save(detailUser);

        // Show
        data = new HashMap<>();
        data.put("firstName", detailUser.getFirstName());
        data.put("lastName", detailUser.getLastName());
        data.put("Phone number", detailUser.getPhoneNumber());
        
        // response data
        responseData = new ResponseData<Object>(HttpStatus.OK.value(), "success update", data);
      }else{
        responseData = new ResponseData<Object>(HttpStatus.NOT_FOUND.value(), "empty data, tidak ada email", null);
      }
    } else {
      responseData = new ResponseData<Object>(HttpStatus.NOT_FOUND.value(), "empty data, tidak ada", null);
    }
    return responseData;
  }

}
