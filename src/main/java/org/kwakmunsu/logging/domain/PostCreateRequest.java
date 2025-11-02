package org.kwakmunsu.logging.domain;

import lombok.Builder;

@Builder
public record PostCreateRequest(String title, String content) {

}