package com.example.kok.mybatis.converter;

import com.example.kok.enumeration.RequestStatus;
import com.example.kok.enumeration.UserRole;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class StringToRequestStatus implements Converter<String, RequestStatus> {
    @Override
    public RequestStatus convert(@NonNull String source) {
        Map<String, RequestStatus> requestStatusMap =
                Stream.of(RequestStatus.values())
                        .collect(Collectors.toMap(RequestStatus::getValue, Function.identity()));

        return requestStatusMap.get(source);
    }
}
