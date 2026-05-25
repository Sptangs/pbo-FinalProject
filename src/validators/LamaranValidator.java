// ===============================
// FILE: validators/LamaranValidator.java
// ===============================

package validators;

public class LamaranValidator {

    public static void validateRequired(
            String value,
            String fieldName) {

        if (value == null || value.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    fieldName + " tidak boleh kosong");
        }
    }

    public static void validateEmail(String email) {

        validateRequired(email, "Email");

        String regex =
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        if (!email.matches(regex)) {

            throw new IllegalArgumentException(
                    "Format email tidak valid");
        }
    }

    public static void validateTelepon(String telepon) {

        validateRequired(telepon, "Telepon");

        String cleaned =
                telepon.replaceAll("[^0-9]", "");

        if (cleaned.length() < 10
                || cleaned.length() > 15) {

            throw new IllegalArgumentException(
                    "Nomor telepon harus 10-15 digit");
        }
    }

    public static void validateLowonganId(int id) {

        if (id <= 0) {

            throw new IllegalArgumentException(
                    "ID lowongan tidak valid");
        }
    }
}