package ru.gb;

@interface RecoverException {
    Class<? extends RuntimeException>[] noRecoverFor() default {};
}
