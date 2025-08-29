<template>
  <div>
    <h2>Lägg till ny kund</h2>
    <form @submit.prevent="addCustomer">
      <input v-model="newCustomer.name" placeholder="Namn" required>
      <input v-model="newCustomer.address" placeholder="Adress" required>
      <input v-model="newCustomer.email" placeholder="E-post" required>
      <input v-model="newCustomer.telephone" placeholder="Telefon" required>
      <button type="submit">Lägg till</button>
    </form>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      newCustomer: {
        name: '',
        address: '',
        email: '',
        telephone: ''
      }
    }
  },
  methods: {
    async addCustomer() {
      try {
        await axios.post('http://localhost:8080/digg/user', this.newCustomer);
        alert('Kund tillagd framgångsrikt!');
        // Återställ formuläret
        this.newCustomer = {
          name: '',
          address: '',
          email: '',
          telephone: ''
        };
        // Här kan du lägga till kod för att uppdatera kundlistan om det behövs
      } catch (error) {
        console.error('Fel vid tillägg av kund:', error);
        alert('Ett fel uppstod vid tillägg av kund.');
      }
    }
  }
}
</script>

<style scoped>
form {
  display: flex;
  flex-direction: column;
  max-width: 300px;
  margin: 0 auto;
}

input {
  margin-bottom: 10px;
  padding: 5px;
}

button {
  padding: 5px 10px;
  background-color: #4CAF50;
  color: white;
  border: none;
  cursor: pointer;
}

button:hover {
  background-color: #45a049;
}
</style>
