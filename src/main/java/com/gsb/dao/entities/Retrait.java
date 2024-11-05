package com.gsb.dao.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
//import jakarta.persistence.*;
@Entity
@DiscriminatorValue("R")
public class Retrait extends Operation {
}