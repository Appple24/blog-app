package com.blogging.Service.Impl;

import com.blogging.Repository.userRepo;
import com.blogging.Service.UserService;
import com.blogging.payloads.UserDto;
import com.blogging.exceptions.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.blogging.entites.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private userRepo userrepo;
    @Autowired
    private ModelMapper modelMapper;

    private users  DtoToUser(UserDto dto)
    {
        users user=this.modelMapper.map(dto,users.class);
//        user.setId(dto.getId());
//        user.setName(dto.getName());
//        user.setAbout(dto.getAbout());
//        user.setEmail(dto.getEmail());
//        user.setPassword(dto.getPassword());
        return user;
    }
     private UserDto UserToUserDto(users user)
    {
        UserDto dtoo=this.modelMapper.map(user,UserDto.class);
//        dto.setId(user.getId());
//
//        dto.setName(user.getName());
//        dto.setAbout(user.getAbout());
//        dto.setEmail(user.getEmail());
//        dto.setPassword(user.getPassword());
        return dtoo;
    }


    @Override
    public UserDto createUser(UserDto dto) {
        users us1=this.DtoToUser(dto);
        users savedUser=this.userrepo.save(us1);
        return this.UserToUserDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto dto, Integer userId) {
       users user=this.userrepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("users","id",userId));
        user.setName(dto.getName());
        user.setAbout(dto.getAbout());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
         users updatedUser=this.userrepo.save(user);
        return this.UserToUserDto(updatedUser);

    }

    @Override
    public UserDto getUserById(Integer userId) {
        users user=this.userrepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("users","id",userId));
        return this.UserToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
       List<users> user=this.userrepo.findAll();
       List<UserDto> dto=new ArrayList<>() ;
       for(users i : user)
       {
           dto.add(this.UserToUserDto(i));
       }
       return dto;

    }

    @Override
    public void deleteUser(Integer userId) {
        users user=this.userrepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("users","id",userId));
        this.userrepo.delete(user);
    }

}
