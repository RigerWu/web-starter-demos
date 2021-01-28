package com.rigerwu.web.rabbitmq.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * created by riger on 2021/1/25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MsgBean implements Serializable {

    private static final long serialVersionUID = -1248468460780960866L;
    private String msgDesc;
}
