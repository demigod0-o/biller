package com.biller.enigma.root.network_layer;

public interface NetworkResponse {

    void onSuccessResponse(String response);

    void onFailedResponse(String response);


}
