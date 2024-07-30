package com.edu.estate_agency.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderRequest {
private  Long idRoom;
private  Long idAgent;
private  String status;
}
