package com.bachar;

import java.time.LocalDateTime;

public record Message(String message,
                      LocalDateTime createdAt) {
}
