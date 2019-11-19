package com.hairui;

public class Repositories {
    String username;
    String reposName;
    String language;
    String starsCount;
    String branchesCount;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getReposName() {
        return reposName;
    }

    public void setReposName(String reposName) {
        this.reposName = reposName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getStarsCount() {
        return starsCount;
    }

    public void setStarsCount(String starsCount) {
        this.starsCount = starsCount;
    }

    public String getBranchesCount() {
        return branchesCount;
    }

    public void setBranchesCount(String branchesCount) {
        this.branchesCount = branchesCount;
    }

    @Override
    public String toString() {
        return "Repositories{" +
                "username='" + username + '\'' +
                ", reposName='" + reposName + '\'' +
                ", language='" + language + '\'' +
                ", starsCount='" + starsCount + '\'' +
                ", branchesCount='" + branchesCount + '\'' +
                '}';
    }
}
