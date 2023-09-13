package ru.practicum.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.dto.user.NewUserRequest;
import ru.practicum.dto.user.UserDto;
import ru.practicum.error.exeption.ConflictException;
import ru.practicum.error.exeption.NotFoundException;
import ru.practicum.model.User;
import ru.practicum.mapper.UserMapper;
import ru.practicum.repository.UserRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> getAll(@Nullable List<Long> ids, Integer from, Integer size) {
        if (Objects.nonNull(ids)) {
            List<User> users = userRepository.findAllById(ids);
            return users.stream().map(userMapper::toUserDto).collect(Collectors.toList());
        } else {
            int page = from / size;
            Page<User> users = userRepository.findAll(PageRequest.of(page, size));
            return users.map(userMapper::toUserDto).getContent();
        }
    }

    @Override
    @Transactional
    public UserDto add(NewUserRequest request) {
        userRepository.findFirst1ByName(request.getName()).ifPresent((user) -> {
            throw new ConflictException("User name already exists");
        });
        User savedUser = userRepository.save(userMapper.toUser(request));
        return userMapper.toUserDto(savedUser);
    }

    @Override
    @Transactional
    public void delete(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found or unavailable"));
        userRepository.delete(user);
    }
}
