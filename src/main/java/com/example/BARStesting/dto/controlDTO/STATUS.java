package com.example.BARStesting.dto.controlDTO;

public enum STATUS {

    receiveOffline,
    sentToKafka,
    receive,
    success,
    successConfirmed,
    fail,
    failConfirmed,
    sentToControl,
    control,
    activeUser,
    partialBlockUser,
    blockUser,
    finalFail,
    finalSuccess,
    informed,
    autoClosed,
    kycInformed

}
