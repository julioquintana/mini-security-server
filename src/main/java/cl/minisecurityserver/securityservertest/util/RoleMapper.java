package cl.minisecurityserver.securityservertest.util;

import cl.minisecurityserver.securityservertest.dao.entity.Role;
import cl.minisecurityserver.securityservertest.dao.entity.pk.RolePK;
import cl.minisecurityserver.securityservertest.dto.RoleRequestDto;
import cl.minisecurityserver.securityservertest.dto.RoleResponseDto;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

public class RoleMapper {
  public static Role build(RoleRequestDto requestDto) {
    return Role.builder()
        .id(RolePK.builder().profileId(requestDto.getProfileId()).build())
        .key(requestDto.getKey())
        .description(requestDto.getDescription())
        .build();
  }

  public static RoleResponseDto build(Role role) {

    return RoleResponseDto.builder()
        .id(role.getId().getId())
        .profileId(role.getId().getProfileId())
        .key(role.getKey())
        .description(role.getDescription())
        .createdAt(role.getCreatedAt())
        .updatedAt(role.getUpdatedAt())
        .build();
  }

  public static Page<RoleResponseDto> build(Page<Role> roles) {

    return new PageImpl<>(
        roles.stream().map(RoleMapper::build).collect(Collectors.toList()),
        roles.getPageable(),
        roles.getTotalElements());
  }

  public static List<RoleResponseDto> build(List<Role> roles) {

    return roles.stream().map(RoleMapper::build).collect(Collectors.toList());
  }
}
