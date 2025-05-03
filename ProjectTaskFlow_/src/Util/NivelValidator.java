package Util;

public class NivelValidator {

    public static boolean podeCriarUsuario(String nivelUsuario) {
        if (nivelUsuario == null) {
            return false;
        }
        return nivelUsuario.equals("1"); // Só nível 1 pode criar
    }
}
