﻿using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class ManagerSpawn : MonoBehaviour {

    F_Swordsman F_Swordsman;

    [Space(-10), Header("Rows for UI")]
    [SerializeField] Text Explanation;
    [SerializeField] GameObject RowOne;
    [SerializeField] GameObject RowTwo;
    [SerializeField] GameObject RowThree;
    [SerializeField] GameObject ButtonsUp;
    [SerializeField] GameObject ButtonsDown;
    bool ButtonDisable = false;
    int RowNumber = 0;

    [Space( 10), Header("Cannon Fodder Enemies")]
    [SerializeField] Transform  E_SpawnLocation;
    [SerializeField] Transform  E_CastleLocationOne;
    [SerializeField] Transform  E_CastleLocationTwo;
    [SerializeField] Transform  E_CastleLocationThree;
    int RowCastle = 0;
    [SerializeField] GameObject E_Gunman;
    [SerializeField] GameObject E_Swordsman;

    [Space( 10), Header("Cannon Fodder Friends")]
    [SerializeField] Transform  F_SpawnLocation;
    [SerializeField] GameObject F_SwordsmanGO;

    [Space( 10), Header("Spawn Elements")]
    [SerializeField] GameObject Hydrogen;
    [SerializeField] GameObject Helium;
    [SerializeField] GameObject Lithium;
    [SerializeField] GameObject Beryllium;
    [Space(-10), Header("       Boron")]
    [SerializeField] GameObject Carbon;
    [SerializeField] GameObject Nitrogen;
    [SerializeField] GameObject Oxygen;
    [SerializeField] GameObject Fluorine;
    [SerializeField] GameObject Neon;
    [SerializeField] GameObject Sodium;
    [SerializeField] GameObject Magnesium;
    [Space(-10), Header("       Aluminium")]
    [SerializeField] GameObject Silicon;
    [SerializeField] GameObject Phosphorus;
    [SerializeField] GameObject Sulphur;
    [SerializeField] GameObject Chlorine;
    [SerializeField] GameObject Argon;

    [Space(10), Header("Buttons Elements")]
    [SerializeField] Button[] Elements;

    void Start()
    {
        F_Swordsman = GetComponent<F_Swordsman>();
        Explanation.gameObject.SetActive(false);
        InvokeRepeating("CannonFodderEnemy",  1, 5);
        InvokeRepeating("CannonFodderFriend", 0, 5);
	}
	
	void Update() 
    {
        SwitchRows();
        if (ButtonDisable == true)
        {   // Whenever an Element is spawned, will disable all buttons for 2 seconds.
            for (int i = 0; i < Elements.Length; i++)
            {   // ButtonDisable == false; in FlashText();
                Elements[i].GetComponent<Image>().color = new Color(200, 200, 200);
                Elements[i].interactable = false;
            }
        }
    }

    #region Spawn Elements
    public void Spawn01H ()
    {
        Explanation.text = "[ Hydrogen ] spawns Grenadiers";
        StartCoroutine(FlashText());
        Instantiate(Hydrogen, new Vector2(F_SpawnLocation.position.x + 0.7f, F_SpawnLocation.position.y), Quaternion.identity);
        Instantiate(Hydrogen, new Vector2(F_SpawnLocation.position.x       , F_SpawnLocation.position.y), Quaternion.identity);
        Instantiate(Hydrogen, new Vector2(F_SpawnLocation.position.x - 0.7f, F_SpawnLocation.position.y), Quaternion.identity);
    }

    public void Spawn02He()
    {
        Explanation.text = "[ Helium ] spawns a BLIMP";
        StartCoroutine(FlashText());
        Instantiate(Helium, new Vector2(F_SpawnLocation.position.x, F_SpawnLocation.position.y + 4.5f), Quaternion.identity);
    }

    public void Spawn03Li()
    {
        Explanation.text = "[ Lithium ] shoots small tazers";
        StartCoroutine(FlashText());
        Instantiate(Lithium, new Vector2(F_SpawnLocation.position.x + 0.5f, F_SpawnLocation.position.y), Quaternion.identity);
        Instantiate(Lithium, new Vector2(F_SpawnLocation.position.x - 0.5f, F_SpawnLocation.position.y), Quaternion.identity);
    }

    public void Spawn04Be()
    {
        Explanation.text = "[ Beryllium ] blinds a bit";
        StartCoroutine(FlashText());
        Instantiate(Beryllium, new Vector2(F_SpawnLocation.position.x + 0.5f, F_SpawnLocation.position.y), Quaternion.identity);
        Instantiate(Beryllium, new Vector2(F_SpawnLocation.position.x - 0.5f, F_SpawnLocation.position.y), Quaternion.identity);
    }

    public void Spawn05B ()
    {
        Explanation.text = "[ Boron ] increases Movement";
        StartCoroutine(FlashText());
        StartCoroutine(ActivateBoron());
    }

    public void Spawn06C ()
    {
        Explanation.text = "[ Carbon ] has some armour";
        StartCoroutine(FlashText());
    }

    public void Spawn07N ()
    {
        Explanation.text = "[ Nitrogen ] reduces some Oxygen";
        StartCoroutine(FlashText());
    }

    public void Spawn08O ()
    {
        Explanation.text = "[ Oxygen ] makes explosions big";
        StartCoroutine(FlashText());
    }

    public void Spawn09F ()
    {
        Explanation.text = "[ Fluorine ] drops a bigger nuke";
        StartCoroutine(FlashText());
    }

    public void Spawn10Ne()
    {
        Explanation.text = "[ Neon ] Lightsaber time";
        StartCoroutine(FlashText());
    }

    public void Spawn11Na()
    {
        Explanation.text = "[ Sodium ] shoots big tazers";
        StartCoroutine(FlashText());
        Instantiate(Sodium, F_SpawnLocation);
    }

    public void Spawn12Mg()
    {
        Explanation.text = "[ Magnesium ] blinds a lot";
        StartCoroutine(FlashText());
    }

    public void Spawn13Al()
    {
        Explanation.text = "[ Aluminium ] increases Attack Speed";
        StartCoroutine(FlashText());
        StartCoroutine(ActivateAluminium());
    }

    public void Spawn14Si()
    {
        Explanation.text = "[ Silicon ] has lots of armour";
        StartCoroutine(FlashText());
    }

    public void Spawn15P ()
    {
        Explanation.text = "[ Phosphorus ] reduces a lot of Oxygen";
        StartCoroutine(FlashText());
    }

    public void Spawn16S ()
    {
        Explanation.text = "[ Sulphur ] makes explosions even bigger";
        StartCoroutine(FlashText());
    }

    public void Spawn17Cl()
    {
        Explanation.text = "[ Chlorine ] drops a small nuke";
        StartCoroutine(FlashText());
    }

    public void Spawn18Ar()
    {
        Explanation.text = "[ Argon ] fire fire";
        StartCoroutine(FlashText());
    }


    IEnumerator ActivateBoron()
    {   // not working, fix
        F_Swordsman.Boron = true;
        yield return new WaitForSeconds(5);
        F_Swordsman.Boron = false;
    }

    IEnumerator ActivateAluminium()
    {   // not working, fix
        F_Swordsman.Aluminium = true;
        yield return new WaitForSeconds(5);
        F_Swordsman.Aluminium = false;
    }
    #endregion

    public void ButtonUp()   { if (RowNumber != 0) { RowNumber--; } }
    public void ButtonDown() { if (RowNumber != 2) { RowNumber++; } }
    void SwitchRows()
    {   // For the UI Buttons.
        switch (RowNumber)
        {
            case 1:     // Second Row.
                ButtonsUp.GetComponent<Image>().color   = new Color(255, 255, 255);
                ButtonsDown.GetComponent<Image>().color = new Color(255, 255, 255);
                RowOne.SetActive(false);
                RowTwo.SetActive(true);
                RowThree.SetActive(false);
                break;
            case 2:     // Third Row.
                ButtonsDown.GetComponent<Image>().color = new Color(0, 0, 0);
                RowOne.SetActive(false);
                RowTwo.SetActive(false);
                RowThree.SetActive(true);
                break;
            default:    // First Row.
                ButtonsUp.GetComponent<Image>().color  = new Color(0, 0, 0);
                RowOne.SetActive(true);
                RowTwo.SetActive(false);
                RowThree.SetActive(false);
                break;
        }
    }

    IEnumerator FlashText()
    {
        Explanation.gameObject.SetActive(true);
        ButtonDisable = true;
        yield return new WaitForSeconds(2);
        Explanation.gameObject.SetActive(false);
        ButtonDisable = false;
        for (int i = 0; i < Elements.Length; i++)
        {
            Elements[i].GetComponent<Image>().color = new Color(255, 255, 255);
            Elements[i].interactable = true;
        }
    }

    void CannonFodderFriend()
    {
        Instantiate(F_SwordsmanGO, F_SpawnLocation);
    }

    void CannonFodderEnemy()
    {
        float RandomSpace = Random.Range(-5, 5);    // For position when in Castle.
        float RandomLocation = Random.Range(0, 5);  // Simulating percentage-based spawning.
        RowCastle = (int) RandomLocation;
        switch (RowCastle)
        {
            case 2:     // First level of Castle.
                Instantiate(E_Gunman, new Vector2(E_CastleLocationOne.position.x + (RandomSpace * 0.1f), 
                E_CastleLocationOne.position.y), Quaternion.identity); break;
            case 3:     // Second level of Castle.
                Instantiate(E_Gunman, new Vector2(E_CastleLocationTwo.position.x + (RandomSpace * 0.1f),
                E_CastleLocationTwo.position.y), Quaternion.identity); break;
            case 4:     // Third level of Castle.
                Instantiate(E_Gunman, new Vector2(E_CastleLocationThree.position.x + (RandomSpace * 0.1f),
                E_CastleLocationThree.position.y), Quaternion.identity); break;
            default:    // Ground level. Spawn E_Gunman or E_Swordsman.
                // Simulating percentage-based spawning.
                int UnitSwitch = Random.Range(0, 3);
                if (UnitSwitch == 0) { Instantiate(E_Gunman,    E_SpawnLocation); }
                if (UnitSwitch == 1) { Instantiate(E_Swordsman, E_SpawnLocation); }
                if (UnitSwitch == 2) { Instantiate(E_Swordsman, E_SpawnLocation); }
                break;
        }
    }
}