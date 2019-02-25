using System.Collections;
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
    int RowNumber = 0;

    [Space( 10), Header("Cannon Fodder Enemies")]
    [SerializeField] Transform  E_SpawnLocation;
    [SerializeField] Transform  E_CastleLocationOne;
    [SerializeField] Transform  E_CastleLocationTwo;
    [SerializeField] Transform  E_CastleLocationThree;
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

    void Start()
    {
        F_Swordsman = GetComponent<F_Swordsman>();
	}
	
	void Update() 
    {
        SwitchRows();
	}

    IEnumerator FlashText()
    {
        Explanation.gameObject.SetActive(true);
        yield return new WaitForSeconds(2);
        Explanation.gameObject.SetActive(false);
    }

    public void ButtonUp()   { if (RowNumber != 0) { RowNumber--; } }
    public void ButtonDown() { if (RowNumber != 2) { RowNumber++; } }
    void SwitchRows()
    {
        switch (RowNumber)
        {
            case 1:
                ButtonsUp.GetComponent<Image>().color   = new Color(255, 255, 255);
                ButtonsDown.GetComponent<Image>().color = new Color(255, 255, 255);
                RowOne.SetActive  (false);
                RowTwo.SetActive  (true );
                RowThree.SetActive(false);
                break;
            case 2:
                ButtonsDown.GetComponent<Image>().color = new Color(0, 0, 0);
                RowOne.SetActive  (false);
                RowTwo.SetActive  (false);
                RowThree.SetActive(true );
                break;
            default:
                ButtonsUp.GetComponent<Image>().color = new Color(0, 0, 0);
                RowOne.SetActive  (true );
                RowTwo.SetActive  (false);
                RowThree.SetActive(false);
                break;
        }
    }

    public void Spawn01H ()
    {
        Explanation.text = "[ Hydrogen ] spawns Grenadiers";
        StartCoroutine(FlashText());
        Instantiate(Hydrogen, new Vector2(F_SpawnLocation.position.x + 1.4f, F_SpawnLocation.position.y), Quaternion.identity);
        Instantiate(Hydrogen, new Vector2(F_SpawnLocation.position.x + 0.7f, F_SpawnLocation.position.y), Quaternion.identity);
        Instantiate(Hydrogen, new Vector2(F_SpawnLocation.position.x + 0.0f, F_SpawnLocation.position.y), Quaternion.identity);
        Instantiate(Hydrogen, new Vector2(F_SpawnLocation.position.x - 0.7f, F_SpawnLocation.position.y), Quaternion.identity);
        Instantiate(Hydrogen, new Vector2(F_SpawnLocation.position.x - 1.4f, F_SpawnLocation.position.y), Quaternion.identity);
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
        Instantiate(Lithium, new Vector2(F_SpawnLocation.position.x + 0.7f, F_SpawnLocation.position.y), Quaternion.identity);
        Instantiate(Lithium, new Vector2(F_SpawnLocation.position.x + 0.0f, F_SpawnLocation.position.y), Quaternion.identity);
        Instantiate(Lithium, new Vector2(F_SpawnLocation.position.x - 0.7f, F_SpawnLocation.position.y), Quaternion.identity);
    }

    public void Spawn04Be()
    {
        Explanation.text = "[ Beryllium ] blinds a bit";
        StartCoroutine(FlashText());
        Instantiate(Beryllium, new Vector2(F_SpawnLocation.position.x + 0.7f, F_SpawnLocation.position.y), Quaternion.identity);
        Instantiate(Beryllium, new Vector2(F_SpawnLocation.position.x + 0.0f, F_SpawnLocation.position.y), Quaternion.identity);
        Instantiate(Beryllium, new Vector2(F_SpawnLocation.position.x - 0.7f, F_SpawnLocation.position.y), Quaternion.identity);
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
}