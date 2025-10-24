package com.messanger.domain.port;

import com.messanger.domain.model.User;

public interface AuthPort {
    User getMe();
}
