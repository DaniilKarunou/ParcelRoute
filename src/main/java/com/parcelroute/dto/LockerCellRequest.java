package com.parcelroute.dto;

import com.parcelroute.model.parcel.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LockerCellRequest {
    private Long lockerId;
    private Size cellSize;
}
