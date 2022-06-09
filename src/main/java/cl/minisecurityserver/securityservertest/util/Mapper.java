package cl.minisecurityserver.securityservertest.util;

import cl.minisecurityserver.securityservertest.dao.entity.Profile;
import cl.minisecurityserver.securityservertest.dto.ProfileRequestDto;
import cl.minisecurityserver.securityservertest.dto.ProfileResponseDto;

public class Mapper {
  public static Profile build(ProfileRequestDto requestDto) {
    return Profile.builder().name(requestDto.getName()).status(true).build();
  }

  public static ProfileResponseDto build(Profile profile) {
    return ProfileResponseDto.builder()
        .id(profile.getId())
        .name(profile.getName())
        .status(profile.getStatus())
        .createdAt(profile.getCreatedAt())
        .updatedAt(profile.getUpdatedAt())
        .build();
  }
}
