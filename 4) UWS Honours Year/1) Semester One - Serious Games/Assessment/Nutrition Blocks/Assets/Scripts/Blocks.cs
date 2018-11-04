using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Blocks : MonoBehaviour {

    Game Points;    // Getting Game.cs
    public bool Empty;
    public GameObject[] Easy;

    void Start()
    {
        Empty = false;
        Points = GameObject.Find("ManagerGame").GetComponent<Game>();
        for (int x = -20; x <= 20; x = x + 5)
        for (int y =   4; y <= 10; y = y + 2)
        {
            Instantiate(Easy[Random.Range(0, Easy.Length)], new Vector2(x, y), Quaternion.identity);
        }

        // At the start, spawn mainly whites, and a couple colours.
        // As points increase, spawn less whites and more colours.
        // check if row is empty. if row is, fill it back up
        // state machine? rpg like, lower difficulty, easier enemies
    }

    void Update() 
    {
		
	}
}