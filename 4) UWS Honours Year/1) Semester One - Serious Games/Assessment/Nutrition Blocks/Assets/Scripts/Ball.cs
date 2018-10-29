using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Ball : MonoBehaviour {

    SpriteRenderer SpriteRenderer;
    public Sprite WhiteDefault; // #e2e2e2
    public Sprite GreenFruit;   // #00ff01
    public Sprite YellowGrain;  // #ffeb01
    public Sprite BlueDairy;    // #00aaff
    public Sprite RedMeat;      // #ff1500
    public Sprite PurpleBad;    // #aa00ff
    public bool CheckWhite;
    public bool CheckGreen;
    public bool CheckYellow;
    public bool CheckBlue;
    public bool CheckRed;
    public bool CheckPurple;

    void Start()
    {
        // for ball to be on paddle, y = -9.648623
        SpriteRenderer = GetComponent<SpriteRenderer>();
        SpriteRenderer.sprite = WhiteDefault;
        CheckWhite = true; CheckGreen = false; CheckYellow = false; CheckBlue = false; CheckRed = false; CheckPurple = false;
    }

    void Update() 
    {
        if (Input.GetKey(KeyCode.Q)) { BallWhite(); }
        if (Input.GetKey(KeyCode.W)) { BallGreen(); }
        if (Input.GetKey(KeyCode.E)) { BallYellow(); }
        if (Input.GetKey(KeyCode.Alpha1)) { BallBlue(); }
        if (Input.GetKey(KeyCode.Alpha2)) { BallRed(); }
        if (Input.GetKey(KeyCode.Alpha3)) { BallPurple(); }
    }

    void OnCollisionEnter2D(Collision2D collision)
    {
        if (collision.gameObject.tag == "block" && CheckWhite == true)
        {
            Debug.Log("Default");
        }

        if (collision.gameObject.tag == "fruit" && CheckGreen == true)
        {
            Debug.Log("Fruit");
        }

        if (collision.gameObject.tag == "grains" && CheckYellow == true)
        {
            Debug.Log("Grains");
        }

        if (collision.gameObject.tag == "dairy" && CheckBlue == true)
        {
            Debug.Log("Dairy");
        }

        if (collision.gameObject.tag == "meat" && CheckRed == true)
        {
            Debug.Log("Meat");
        }

        if (collision.gameObject.tag == "bad" && CheckPurple == true)
        {
            Debug.Log("Bad");
        }
    }

    public void BallWhite()
    {
        SpriteRenderer.sprite = WhiteDefault;
        CheckWhite = true; CheckGreen = false; CheckYellow = false; CheckBlue = false; CheckRed = false; CheckPurple = false;
    }

    public void BallGreen()
    {
        SpriteRenderer.sprite = GreenFruit;
        CheckWhite = false; CheckGreen = true; CheckYellow = false; CheckBlue = false; CheckRed = false; CheckPurple = false;
    }

    public void BallYellow()
    {
        SpriteRenderer.sprite = YellowGrain;
        CheckWhite = false; CheckGreen = false; CheckYellow = true; CheckBlue = false; CheckRed = false; CheckPurple = false;
    }

    public void BallBlue()
    {
        SpriteRenderer.sprite = BlueDairy;
        CheckWhite = false; CheckGreen = false; CheckYellow = false; CheckBlue = true; CheckRed = false; CheckPurple = false;
    }

    public void BallRed()
    {
        SpriteRenderer.sprite = RedMeat;
        CheckWhite = false; CheckGreen = false; CheckYellow = false; CheckBlue = false; CheckRed = true; CheckPurple = false;
    }

    public void BallPurple()
    {
        SpriteRenderer.sprite = PurpleBad;
        CheckWhite = false; CheckGreen = false; CheckYellow = false; CheckBlue = false; CheckRed = false; CheckPurple = true;
    }
}