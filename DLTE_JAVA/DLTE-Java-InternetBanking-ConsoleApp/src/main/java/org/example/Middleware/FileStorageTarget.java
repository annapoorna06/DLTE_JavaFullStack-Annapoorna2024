package org.example.Middleware;

import org.example.Middleware.UserDetailsFileRepository;
import org.example.Remote.StorageTarget;
import org.example.Remote.UserDetailsRepository;
//create file to store data
public class FileStorageTarget implements StorageTarget {
    @Override
    public UserDetailsRepository getUserDetailsRepository()
    {
        return new UserDetailsFileRepository("userdetail.doc");
    }
}

