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
        CheckWhite = true;
        CheckGreen = false;
        CheckYellow = false;
        CheckBlue = false;
        CheckRed = false;
        CheckPurple = false;
    }

    void Update() 
    {
        BallChange();
	}

    void BallChange()
    {
        if (Input.GetKey(KeyCode.Q)) { BallWhite(); }
        if (Input.GetKey(KeyCode.W)) { SpriteRenderer.sprite = GreenFruit; }
        if (Input.GetKey(KeyCode.E)) { BallYellow(); }
        if (Input.GetKey(KeyCode.Alpha1)) { SpriteRenderer.sprite = BlueDairy; }
        if (Input.GetKey(KeyCode.Alpha2)) { SpriteRenderer.sprite = RedMeat; }
        if (Input.GetKey(KeyCode.Alpha3)) { SpriteRenderer.sprite = PurpleBad; }
    }

    void OnCollisionEnter2D(Collision2D collision)
    {
        if (collision.gameObject.name == "Block Grains" && CheckYellow == true)
        {

        }
    }

    public void BallWhite()
    {
        SpriteRenderer.sprite = WhiteDefault;
        CheckWhite = true;
        CheckGreen = false;
        CheckYellow = false;
        CheckBlue = false;
        CheckRed = false;
        CheckPurple = false;
    }

    public void BallYellow()
    {
        SpriteRenderer.sprite = YellowGrain;
        CheckWhite = false;
        CheckGreen = false;
        CheckYellow = true;
        CheckBlue = false;
        CheckRed = false;
        CheckPurple = false;
    }
}