using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ManagerGame : MonoBehaviour {

    public float TimePlayed;
    public float ScoreCurrent;
    public int CounterEnemiesKilled;
    public int CounterFriendsKilled; // Collateral Damage

    void Start()
    {
        // Counter for the Game Data, and will be passed onto for local High Score.
        // How to make sure "tutorial-like" pop-up shows up only once?
        DontDestroyOnLoad(this);
	}
	
	void Update() 
    {
		
	}
}