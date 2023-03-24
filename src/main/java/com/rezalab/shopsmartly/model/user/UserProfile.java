package com.rezalab.shopsmartly.model.user;

import com.rezalab.shopsmartly.model.base.BaseModel;
import com.rezalab.shopsmartly.model.master.File;
import com.rezalab.shopsmartly.model.master.Role;

import javax.persistence.*;

@Entity
@Table(name = "user_profile")
public class UserProfile extends BaseModel {
    @Column(name = "user_name", unique = true)
    private String name;
    @Column(name = "address", columnDefinition = "TEXT")
    private String address;
    @Column(name = "second_address", columnDefinition = "TEXT")
    private String secondAddress;
    @ManyToOne
    @JoinColumn(name = "file_id")
    private File file;

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getAddress() {return address;}

    public void setAddress(String address) {this.address = address;}

    public String getSecondAddress() {return secondAddress;}

    public void setSecondAddress(String secondAddress) {this.secondAddress = secondAddress;}

    public File getFile() {return file;}

    public void setFile(File file) {this.file = file;}
}
