package it.unibo.deathnote.impl;

import java.util.ArrayList;
import java.util.List;

import it.unibo.deathnote.api.DeathNote;

public class DeathNoteImplementation implements DeathNote{

    private final static long TIME_MAX_CAUSE = 40;
    private final static long TIME_MAX_DETAILS = 6040;

    
    private final List<Victim> victims;
    
    public DeathNoteImplementation() {
        victims = new ArrayList<>();
    }
    
    private class Victim {
        private final String name;
        private String causeOfDeath;
        private String detailsOfDeath;
        private final long timeOfDeath;

        public Victim (final String name) {
            this.name = name;
            this.causeOfDeath = "heart attack";
            this.detailsOfDeath = "";
            this.timeOfDeath = System.currentTimeMillis();
        }

        public String getName() {
            return this.name;
        }

        public String getCauseOfDeath() {
            return this.causeOfDeath;
        }

        public String getDetailsOfDeath() {
            return this.detailsOfDeath;
        }

        public long getTimeOfDeath() {
            return this.timeOfDeath;
        }

        public void setCauseOfDeath(final String causeOfDeath) {
            this.causeOfDeath = causeOfDeath;
        }

        public void setDetalisOfDeath(final String deatilsOfDeath) {
            this.detailsOfDeath = deatilsOfDeath;
        }
 
    }

    @Override
    public String getRule(final int ruleNumber) {
        if (ruleNumber <= 0 || ruleNumber > DeathNote.RULES.size()) {
            throw new IllegalArgumentException("The rule searched is non-existent");
        }
        return DeathNote.RULES.get(ruleNumber - 1);
    }

    @Override
    public void writeName(final String name) {
        if (name == null) {
            throw new NullPointerException("You should write the name of the victim");
        }
        Victim victim = new Victim(name);
        this.victims.add(victim);
    }

    @Override
    public boolean writeDeathCause(final String cause) {
        if (this.victims.isEmpty() || cause == null) {
            throw new IllegalStateException("There is no victim or the cause is inconsisten");
        }
        Victim currentVictim = victims.getLast(); 
        if (System.currentTimeMillis() - currentVictim.getTimeOfDeath() < TIME_MAX_CAUSE) {
            currentVictim.setCauseOfDeath(cause);
            return true;
        }
        return false;
    }

    @Override
    public boolean writeDetails(final String details) {
        if (this.victims.isEmpty() || details == null) {
            throw new IllegalStateException("There is no victim or the cause is inconsisten");
        }
        Victim currentVictim = victims.getLast();
        if (System.currentTimeMillis() - currentVictim.getTimeOfDeath() < TIME_MAX_DETAILS) {
            currentVictim.setDetalisOfDeath(details);
            return true;
        }
        return false;
    }

    @Override
    public String getDeathCause(final String name) {
        if (name == null || !isNameWritten(name)) {
            throw new IllegalArgumentException("There is no such victim in the DeathNote or invalid name");
        }
        String causeOfDeath = new String();
        for (Victim currentVictim : victims) {
            if (currentVictim.getName().equals(name)) {
                causeOfDeath = currentVictim.getCauseOfDeath();
            }
        }
        return causeOfDeath;
    }

    @Override
    public String getDeathDetails(final String name) {
        if (name == null || !isNameWritten(name)) {
            throw new IllegalArgumentException("There is no such victim in the DeathNote or invalid name");
        }
        String detailsOfDeath = new String();
        for (Victim currentVictim : victims) {
            if (currentVictim.getName().equals(name)) {
                detailsOfDeath = currentVictim.getDetailsOfDeath();
            }
        }
        return detailsOfDeath;
        
    }

    @Override
    public boolean isNameWritten(String name) {
        for (Victim currentVictim : victims) {
            if (currentVictim.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

}