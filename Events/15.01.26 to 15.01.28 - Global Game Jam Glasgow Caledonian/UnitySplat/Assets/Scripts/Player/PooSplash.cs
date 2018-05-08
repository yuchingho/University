/*
 * This script should attach to player body.
 * Ensure the poop thrown by baby is tagged as "poop"
 */
using UnityEngine;
using UnityEngine.UI;
using System.Collections;

public class PooSplash : MonoBehaviour {

    public Image TargetImage;

    private bool bFadeIn = false;
    private bool bFadeOut = false;
    private bool bIsActive = false;

    private float FadeInTimer = 0;
    public float FadeInTime = 1f;

    private float DurationTimer = 0f;
    public float DurationTime = 2f;

    private float FadeOutTimer = 0;
    public float FadeOutTime = 3f;
    private float FadeOutAlpha = 0f;


	// Use this for initialization
	void Start () {
        FadeOutAlpha = FadeOutTime;
	}
	
	// Update is called once per frame
	void Update () {

        if(bFadeIn)
        {
            UpdateFadeIn();
        }
        else if(bIsActive)
        {
            UpdateActive();
        }
        else if(bFadeOut)
        {
            UpdateFadeOut();
        }
	
	}

    void UpdateFadeIn()
    {

        FadeInTimer += Time.deltaTime;
        if (FadeInTimer >= FadeInTime)
        {
            bFadeIn = false;
            bIsActive = true;
            FadeInTimer = 0f;
        }
        else
        {
            float percentage = (FadeInTimer / 255);
            AdjustColor(255 * percentage);
        }

    }

    void UpdateActive()
    {

        DurationTimer += Time.deltaTime;
        if(DurationTimer >= DurationTime)
        {
            bIsActive = false;
            bFadeOut = true;
            DurationTimer = 0f;
        }

    }

    void UpdateFadeOut()
    {

        FadeOutTimer += Time.deltaTime;
        FadeOutAlpha -= Time.deltaTime;
        if (FadeOutTimer >= FadeOutTime)
        {
            Reset();
        }
        else
        {
            float percentage = (FadeOutAlpha / 255);
            AdjustColor(255 * percentage);
        }

    }


    void OnCollisionEnter2D(Collision2D col)
    {

        if (col.gameObject.tag == "Poop")
        {

            if(TargetImage != null)
            {
                Reset();
                this.bFadeIn = true;
            }

        }

    }

    private void AdjustColor(float alpha)
    {
        Color newColor = new Color(255, 255, 255, alpha);
        TargetImage.color = newColor;
    }


    private void Reset()
    {
        AdjustColor(0);

        bFadeIn = false;
        bFadeOut = false;
        bIsActive = false;

        DurationTimer = 0f;
        FadeInTimer = 0f;
        FadeOutTimer = 0f;
    }
}
