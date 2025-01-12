package com.capgemini.wsb.fitnesstracker.user.api;

import java.time.LocalDate;

public record UserDetailsDto(String firstName, LocalDate birthDate, String email) { }

