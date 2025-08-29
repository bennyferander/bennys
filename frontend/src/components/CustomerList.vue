<template>
  <div>
    <h2>Kundlista</h2>
    <ul>
      <li v-for="customer in customers" :key="customer.email">
        {{ customer.name }} - {{ customer.email }}
      </li>
    </ul>
    <div>
      <button @click="prevPage" :disabled="currentPage === 0">Föregående</button>
      <button @click="nextPage" :disabled="!hasMoreCustomers">Nästa</button>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      customers: [],
      currentPage: 0,
      pageSize: 10,
      hasMoreCustomers: true
    }
  },
  methods: {
    async fetchCustomers() {
      try {
        const response = await axios.get(`http://localhost:8080/digg/user?page=${this.currentPage}&size=${this.pageSize}`);
        this.customers = response.data;
        this.hasMoreCustomers = this.customers.length === this.pageSize;
      } catch (error) {
        console.error('Error fetching customers:', error);
      }
    },
    prevPage() {
      if (this.currentPage > 0) {
        this.currentPage--;
        this.fetchCustomers();
      }
    },
    nextPage() {
      this.currentPage++;
      this.fetchCustomers();
    }
  },
  mounted() {
    this.fetchCustomers();
  }
}
</script>
