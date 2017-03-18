package logic;
/**
 * 2/28/17
 * 
 *
 */
public enum Role {

    TOWNIE              ("Townie", "Do nothing at night", "Lynch all Mafia to win the game"),
    DETECTIVE           ("Detective", "Reveals the team for one player per night", "Lynch all Mafia to win the game"),
    MAFIA_HITMAN        ("Mafia: Hitman", "May kill someone each night", "To make the majority of the town mafia members"),
    DOCTOR              ("Doctor", "May heal one player each night", "Lynch all Mafia to win the game"),
    SURVIVOR            ("Survivor", "Do nothing at night", "To be the last town member left alive"),
    MAFIA_BARMAN        ("Mafia: Barman", "May stop the action of another player each night", "To make the majority of the town mafia members"),
    BODYGUARD           ("Bodyguard", "May save another person by dying in their place", "Lynch all Mafia to win the game"),
    LYNCHER             ("Lyncher", "Do nothing at night", "To Lynch a specific player to win solo win the game | "),
    MAFIABOSS_GODFATHER ("Mafia- GodFather", "Hidden from the Detective", "To make the majority of the town mafia members"),
    VIGILANTE           ("Vigilante", "May kill new person each night", "Lynch all Mafia to win the game");

    private final String roleID;
    private final String roleInfo;
    private final String roleGoal;

    Role(String name, String role, String goal) {
        this.roleID = name;
        this.roleInfo = role;
        this.roleGoal = goal;
    }

    public String getRoleID() {
        return roleID;
    }

    public String getRoleInfo() {
        return roleInfo;
    }

    public String getRoleGoal() {
        return roleGoal;
    }
}