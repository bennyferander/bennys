<template>
  <div>
    <nav>
      <button @click="currentView = 'add'">Lägg till kund</button>
      <button @click="currentView = 'list'">Visa kundlista</button>
    </nav>

    <div v-if="currentView === 'add'">
      <AddCustomer @customer-added="updateList" />
    </div>

    <div v-else>
      <CustomerList ref="customerList" />
    </div>
  </div>
</template>

<script>
import AddCustomer from './components/AddCustomer.vue'
import CustomerList from './components/CustomerList.vue'

export default {
  components: { AddCustomer, CustomerList },
  data() {
    return {
      currentView: 'add' // standardvy = formuläret
    }
  },
  methods: {
    updateList() {
      this.$refs.customerList.refreshList()
      this.currentView = 'list' // växla automatiskt till listan efter tillägg
    }
  }
}
</script>

<style>
nav {
  margin-bottom: 20px;
}
nav button {
  margin-right: 10px;
  padding: 5px 10px;
  cursor: pointer;
}
</style>
