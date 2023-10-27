package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString

public class ErrorDTO {

    int  status;
    String error;
    Object message;

//"timestamp": "2023-10-23T14:44:41.434Z",
//  "status": 0,
//  "error": "string",
//  "message": {},
//  "path": "string"
}
