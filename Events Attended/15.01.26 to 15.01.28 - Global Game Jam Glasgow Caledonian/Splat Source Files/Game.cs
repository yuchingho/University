using UnityEngine;
using System.Collections;

public static class Game  
{
    public static GameObject Player;
    public static GameObject PlayerBody;

    public static int NumberOfProps = 0;
    public static int MaxProps = 3;
    public static int NumberOfEnemys = 0;
    public static int MaxEnemys = 2;

    public static int DistanceToGround = 1000;
    public static bool bIsPlayerDead = false;

    public static bool HasChute = false;


    static Game()
    {
        Player = GameObject.Find("Player");
        PlayerBody = Player.transform.FindChild("Body").gameObject;
    }


    public static void RemoveEnemyOrProp(string tag)
    {
        if (tag == "Enemy")
        {
            --Game.NumberOfEnemys;
        }
        else
        {
            --Game.NumberOfProps;
        }
    }

    public static void GameOver()
    {
        if (HasChute)
        {
            Application.LoadLevel("Win");
        }
        else
        {
            Application.LoadLevel("Lose");
        }
    }
}
