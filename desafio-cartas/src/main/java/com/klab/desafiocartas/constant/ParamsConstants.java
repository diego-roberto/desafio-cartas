package com.klab.desafiocartas.constant;

public class ParamsConstants {

    private ParamsConstants() {
        throw new IllegalStateException("Utility class");
    }

//    private static final String NUMBER_OF_PLAYERS = System.getenv("NUMBER_OF_PLAYERS");
//    private static final String DRAW_SIZE = System.getenv("DRAW_SIZE");

    // Atribui um valor hardcoded caso as variáveis de ambiente não estejam acessíveis (mais para casos de teste fora do container)
    public static int getEnvInt(String envVarName, int defaultValue) {
        String envValue = System.getenv(envVarName);
        if (envValue == null) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(envValue);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("A variável de ambiente '" + envVarName + "' não é um número válido.", e);
        }
    }

    public static int getNumberOfPlayers() {
        return getEnvInt("NUMBER_OF_PLAYERS", 4);
    }

    public static int getDrawSize() {
        return getEnvInt("DRAW_SIZE", 5);
    }
}

