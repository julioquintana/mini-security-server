package cl.minisecurityserver.securityservertest.util;

import cl.minisecurityserver.securityservertest.dao.entity.Profile;
import cl.minisecurityserver.securityservertest.dto.ProfileRequestDto;
import cl.minisecurityserver.securityservertest.dto.ProfileResponseDto;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

public class ProfileMapper {
  public static Profile build(ProfileRequestDto requestDto) {
    return Profile.builder().name(requestDto.getName()).status(true).build();
  }

  public static ProfileResponseDto build(Profile profile) {
    return ProfileResponseDto.builder()
        .id(profile.getId())
        .name(profile.getName())
        .roles(RoleMapper.build(profile.getRoles()))
        .status(profile.getStatus())
        .createdAt(profile.getCreatedAt())
        .updatedAt(profile.getUpdatedAt())
        .build();
  }

  public static Page<ProfileResponseDto> build(Page<Profile> profileResponse) {

    return new PageImpl<>(
        profileResponse.stream().map(ProfileMapper::build).collect(Collectors.toList()),
        profileResponse.getPageable(),
        profileResponse.getTotalElements());
  }
}
