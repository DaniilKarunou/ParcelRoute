package com.parcelroute.dto;

import com.parcelroute.model.parcel.ParcelType;
import com.parcelroute.model.parcel.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParcelRequest {
    private Long lockerId;
    private Size size;
    private ParcelType parcelType;
    private String senderEmail;
    private String recipientEmail;
}

