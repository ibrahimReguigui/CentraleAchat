////    @Override
////    @Transactional
////    public void deleteUser(Long idUser) {
////        User user=userRepository.findById(idUser).get();
////        switch (user.getRole()){
////            case SUPPLIER:
////                apiInventoryService.deleteAllByIdSupplier(user.getId());
////                companyRepository.delete(user.getCompany());
////                userRepository.deleteAllByCompanyIdCompany(user.getCompany().getIdCompany());
////                break;
////            default:
////                userRepository.delete(user);
////        }
////    }



//for (UserSessionRepresentation session : sessions) {
//                    Date start = new Date((session.getStart())* 1000L);
//                    Date end = new Date((session.getLastAccess())* 1000L);
//                    System.out.println("start Date value: " + start);
//                    System.out.println("end Date value: " + end);
//                }
//





//public String fonctionTestAPIDonnation() {
//        return apiDonnationService.registerCharityAssociation();
//        }
//
//@Override
//public void deleteAllUsersExeptAdmin() {
//        List<UserRepresentation> users = keycloak.realm("pidev").users().list();
//        for (UserRepresentation user : users) {
//        if (!user.getUsername().equals("systemadmin")) {
//        keycloak.realm("pidev").users().get(user.getId()).remove();
//        }
//        }
//        }
//
//@Override
//public void whoAmI() {
//        KeycloakAuthenticationToken authentication = (KeycloakAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
//        KeycloakSecurityContext keycloakSecurityContext = authentication.getAccount().getKeycloakSecurityContext();
//        Set<String> roles = keycloakSecurityContext.getToken().getRealmAccess().getRoles();
//        System.out.println(keycloakSecurityContext.getToken().getEmail());
//        System.out.println(keycloakSecurityContext.getToken().getSubject());
//        System.out.println(keycloakSecurityContext.getToken().getIssuedFor());
//        System.out.println(keycloakSecurityContext.getToken().getName());
//        System.out.println(keycloakSecurityContext.getToken().getAuthorization());
//        System.out.println(roles.toString());
//        System.out.println(keycloakSecurityContext.getToken().getOtherClaims().get("idCompany"));
//        }
//
//
//@Override

//
//@Override

//public class BlocNote {
//
//    @GetMapping("/deleteAllUsersExeptAdmin")
//    public ResponseEntity<String> deleteAllUsersExeptAdmin() {
//        userService.deleteAllUsersExeptAdmin();
//        return ResponseEntity.status(HttpStatus.GONE).body("DELETED");
//    }

////    @PostMapping("/login")
////    public String authenticate(@RequestParam String username,@RequestParam String password) throws IOException {
////        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
////        Authentication authentication = authenticationManager.authenticate(token);
////        SecurityContextHolder.getContext().setAuthentication(authentication);
////
////        AccessToken accessToken = keycloakConfig.whoAmI();
////        return accessToken.toString();
////
////    }
//
//
//
//
//
//
//
//
//
//
//
//    @GetMapping("get/{id}")
//    //  @RolesAllowed({"admin","ADMIN","admin"})
//    public String getById(@PathVariable String id, Principal principal, HttpSession httpSession, HttpServletRequest request) {
//        System.out.println(principal.getName()+httpSession.getAttributeNames());
//        KeycloakAuthenticationToken authentication = (KeycloakAuthenticationToken) request.getUserPrincipal();
//        String username = authentication.getAccount().getKeycloakSecurityContext().getToken().getEmail();
//        String idb = authentication.getAccount().getKeycloakSecurityContext().getToken().getSubject();
//        System.out.println(username+idb);
//        Keycloak keycloak = KeycloakBuilder.builder()
//                .serverUrl("http://localhost:8099/")
//                .realm("pidev")
//                .clientId("pidev")
//                .clientSecret("unvXYGjaDZZgZRgxBY2tzVFvqAYwPUFt")
//                .username("sytemadmin")
//                .password("admin")
//                .build();
//        try {
//            UserResource userResource = keycloak.realm("pidev").users().get(id);
//            UserRepresentation user = userResource.toRepresentation();
//            return user.getId() + " " + user.getUsername() + " " + user.getAttributes();
//        } catch (Exception e) {
//            log.error(e.getMessage() + " " + e.fillInStackTrace());
//            return e.getMessage() + " " + e.fillInStackTrace();
//        }
//    }
//
//    @GetMapping("get")
//    @RolesAllowed({"SUPPLIER"})
//    public String getAll(Principal principal) {
//        KeycloakAuthenticationToken authentication = (KeycloakAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
//        KeycloakSecurityContext keycloakSecurityContext = authentication.getAccount().getKeycloakSecurityContext();
//        Token token=keycloakSecurityContext.getToken();
//        Set<String> roles = keycloakSecurityContext.getToken().getRealmAccess().getRoles();
//        System.out.println(keycloakSecurityContext.getToken().getEmail());
//        System.out.println(keycloakSecurityContext.getToken().getSubject());
//        System.out.println(keycloakSecurityContext.getToken().getIssuedFor());
//        System.out.println(keycloakSecurityContext.getToken().getName());
//        System.out.println(keycloakSecurityContext.getToken().getAuthorization());
//        System.out.println(roles.toString());
//        System.out.println(principal.getName());
//        System.out.println(keycloakSecurityContext.getToken().isVerifyCaller());
//        if (roles.contains("admin")) {
//            return "User has the correct role";
//        } else {
//            return "User does not have the correct role";
//        }
////        Keycloak keycloak = KeycloakBuilder.builder()
////                .serverUrl("http://localhost:8099/")
////                .realm("pidev")
////                .clientId("pidev")
////                .clientSecret("unvXYGjaDZZgZRgxBY2tzVFvqAYwPUFt")
////                .username("admin")
////                .password("admin")
////                .build();
////        try {
////            UsersResource usersResource = keycloak.realm("pidev").users();
////            List<UserRepresentation> users = usersResource.search("", false);
////            for (UserRepresentation user : users) {
////                System.out.println(user.getId() + " " + user.getUsername() + " " + user.getAttributes());
////            }
////            return users.toString();
////        } catch (Exception e) {
////            log.error(e.getMessage() + " " + e.fillInStackTrace());
////            return e.getMessage() + " " + e.fillInStackTrace();
////        }
//    }
//
//    @GetMapping("/create")
////    public String createUser() {
////        Keycloak keycloak = KeycloakBuilder.builder()
////                .serverUrl("http://localhost:8099/")
////                .realm("pidev")
////                .clientId("pidev")
////                .clientSecret("unvXYGjaDZZgZRgxBY2tzVFvqAYwPUFt")
////                .username("sytemadmin")
////                .password("admin")
////                .build();
////
////        UserRepresentation user = new UserRepresentation();
////
////        user.setUsername("joo");
////        // user.setEmail("myuser@example.com");
////        //user.setFirstName("jo");
////        //user.setLastName("doe");
//////        HashMap<String,List<String>> attributes=new HashMap<>();
//////        attributes.put("Role", Collections.singletonList((Role.CLIENT.toString())));
//////        attributes.put("idCompany", Collections.singletonList("1"));
//////        user.setAttributes(attributes);
////        user.setEnabled(true);
////
////        CredentialRepresentation passwordCred = new CredentialRepresentation();
////        passwordCred.setTemporary(false);
////        passwordCred.setType(CredentialRepresentation.PASSWORD);
////        passwordCred.setValue("jo");
////
////        user.setCredentials(Arrays.asList(passwordCred));
////
////        RolesResource realmRolesResource = keycloak.realm("pidev").roles();
////        List<RoleRepresentation> realmRoles = realmRolesResource.list();
////
////        // Find the "user" realm role and set it for the user
////
////        for (RoleRepresentation role : realmRoles) {
////            System.out.println(role.getName());
////        }
////        RoleRepresentation role = keycloak.realm("pidev").roles().get("CLIENT").toRepresentation();
////        user.setRealmRoles(Collections.singletonList(role.getName()));
////
////        UsersResource usersResource = keycloak.realm("pidev").users();
////        Response response = usersResource.create(user);
////        String createdUserId = CreatedResponseUtil.getCreatedId(response);
////
//////        List<UserRepresentation> users = keycloak.realm("pidev").users().search(user.getUsername());
//////
//////        keycloak.realm("pidev").users().get(user.getId()).roles().realmLevel().add(Collections.singletonList(role));
//////        keycloak.realm("pidev").users().get(user.getId()).update(user);
////
////        return createdUserId;
////    }
//    @GetMapping("auth")
//    public String auth(@RequestParam String username, @RequestParam String password) {
//
//
//        Keycloak keycloak = KeycloakBuilder.builder()
//                .serverUrl("http://localhost:8099/")
//                .realm("pidev")
//                .clientId("pidev")
//                .clientSecret("unvXYGjaDZZgZRgxBY2tzVFvqAYwPUFt")
//                .username(username)
//                .password(password)
//                .build();
//
//        TokenManager tokenManager = keycloak.tokenManager();
//        AccessTokenResponse accessToken = tokenManager.getAccessToken();
//        System.out.println(accessToken.getIdToken()+" "+accessToken.getExpiresIn()+" "+accessToken.getScope()+
//                " "+accessToken.getToken());
//        return accessToken.toString();
//    }
//
//
////    @PostMapping("/update")
////    @ResponseStatus(HttpStatus.OK)
////    public UserDto updateUser(@Valid @RequestBody UserDto userDto) {
////        return userService.addUser(userDto);
////    }
//
////    @DeleteMapping("/delete")
////    @ResponseStatus(HttpStatus.OK)
////    public void deleteUser(@RequestParam Long idUser) {
////        userService.deleteUser(idUser);
////    }
//
//}
